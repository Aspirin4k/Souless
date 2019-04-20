package souless.game.model.save;

import com.badlogic.gdx.math.Vector2;
import souless.game.model.meta.SavedGameObject;
import souless.game.model.meta.SavedTexture;
import souless.game.bundle.world.entity.World;
import souless.game.objects.Entity;
import souless.game.objects.GameObjectFactory;
import souless.game.objects.Tile;
import souless.game.view.objects.ViewObjectFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SaveGameParser {
    /*
    Множество возможных токенов в файле сохранения
    */
    private enum Token {
        map,
        map_height,
        map_width,
        entities,
        textures,
        object_types
    };
    
    // Хешмап с параметрами
    private final HashMap<Token, String> parameters;
    // Хешмап с описанием типов объектов, используемых на карте
    private final HashMap<String, SavedGameObject> objects;
    // Хешмап с описанием текстур, используемых на карте
    private final HashMap<String, SavedTexture> textures;

    public SaveGameParser() {
        this.textures = new HashMap<String, SavedTexture>();
        this.parameters = new HashMap<Token, String>();
        this.objects = new HashMap<String, SavedGameObject>();

        this.textures.put("CONSOLE_LOG", new SavedTexture("console", ViewObjectFactory.viewType.SIMPLE, "ui"));
    }

    /**
     * Метод позволяет сформировать список пар ИДЕНТИФИКАТОР-ТЕКСТУРА. Идентификаторы используются в блоке описания игровых объектов.
     * Текстура содержит название текстуры и тип отображения (обычный, анимация, прилегающий к соседним таким же и т.д.)
     * @throws Exception 
     */
    private void parseTextures() throws Exception {
        if (!this.parameters.containsKey(Token.textures)) throw new Exception("No texture block was defined");
        String[] textureRows = this.parameters.get(Token.textures).split("\n");

        for (String textureRow : textureRows)
        {
            textureRow = textureRow.replace("\n", "").trim();
            if ("".equals(textureRow)) continue;
            String[] textureTokens = textureRow.split("=");
            if (textureTokens.length != 2) throw new Exception("No texture id was defined or several was");
            if (this.textures.containsKey(textureTokens[0].trim())) throw new Exception("Several textures with the same id");

            String[] args = textureTokens[1].trim().replace('\t',' ').split(" +");
            if (args.length != 3) throw new Exception("Invalid count of arguments for texture (Expected 3)");

            this.textures.put(textureTokens[0].trim(), new SavedTexture(args[0].trim(), Integer.parseInt(args[1].trim()), args[2].trim()));
        }
    }

    /**
     * Метод позволяет сформировать список пар ИДЕНТИФИКАТОР-ОБЪЕКТ. Идентификаторы используются в блоке описания игровой карты.
     * Объект содержит идентификатор текстуры и тип объекта (стена, пол, факел и т.д.)
     * @throws Exception 
     */
    private void parseObjects() throws Exception {
        if (!this.parameters.containsKey(Token.object_types)) throw new Exception("No object types block was defined");
        String[] objectRows = this.parameters.get(Token.object_types).split("\n");

        for (String objectRow : objectRows)
        {
            objectRow = objectRow.replace("\n", "").trim();
            if ("".equals(objectRow)) continue;
            String[] objectTokens = objectRow.split("=");
            if (objectTokens.length != 2) throw new Exception("No object id was defined or several was");
            if (this.objects.containsKey(objectTokens[0].trim())) throw new Exception("Several objects with the same id");

            String[] args = objectTokens[1].trim().replace('\t',' ').split(" +");
            if (args.length != 2) throw new Exception("Invalid count of arguments for object (Expected 2)");
            if (!this.textures.containsKey(args[0].trim())) throw new Exception("Object uses texture id, that was not defined");

            this.objects.put(objectTokens[0].trim(), new SavedGameObject(args[0].trim(), Integer.parseInt(args[1].trim())));
        }
    }

    /**
     * Метод позволяет сформировать игровую карту. 
     * @return
     * @throws Exception 
     */
    private ArrayList<Tile> parseMap() throws Exception {
        ArrayList<Tile> result = new ArrayList<Tile>();
        if (!this.parameters.containsKey(Token.map)) throw new Exception("Map block was not defiened");
        if (!this.parameters.containsKey(Token.map_height)) throw new Exception("Map's height was not defiened");
        if (!this.parameters.containsKey(Token.map_width)) throw new Exception ("Map's width was not defined");

        int mapWidth = Integer.parseInt(this.parameters.get(Token.map_width));
        int mapHeight = Integer.parseInt(this.parameters.get(Token.map_height));
        ArrayList<String> mapRows = new ArrayList<String>(Arrays.asList(this.parameters.get(Token.map).split("\n")));
        for (String mapRow : mapRows) mapRow = mapRow.replace("\n", "").trim();
        while (mapRows.remove(""));
        if (mapRows.size() != mapHeight) throw new Exception("Actual height of map not equal to defined");
        for (int j=mapHeight-1; j>=0; j--)
        {
            String[] mapTokens = mapRows.get(j).trim().replace('\t',' ').split(" +");
            if (mapTokens.length != mapWidth) throw new Exception("Actual width of map not equal to defined");
            for (int i=0; i< mapWidth; i++)
            {
                String mapObjectId = mapTokens[i].trim();
                if (!this.objects.containsKey(mapObjectId)) throw new Exception("Object Id was not defined");
                SavedGameObject mapObject =  this.objects.get(mapObjectId);

                result.add(i,
                        (Tile) GameObjectFactory.getObject(
                                mapObject.getObjectType(),
                                new Vector2(i,mapHeight-j-1),
                                mapObject.getTextureId()));
            }
        }

        return result;
    }

    /**
     * Метод позволяет сформировать список сущностей, присутствующих на карте
     * @return 
     */
    private ArrayList<Entity> parseEntities() throws Exception {
        ArrayList<Entity> result = new ArrayList<Entity>();
        if (!this.parameters.containsKey(Token.entities)) throw new Exception("Entities block was not defined");

        String[] entitiesRows = this.parameters.get(Token.entities).split("\n");
        for (String entityRow : entitiesRows)
        {
            entityRow = entityRow.replace("\n", "").trim();
            if ("".equals(entityRow)) continue;
            String[] args = entityRow.replace('\t',' ').split(" +");
            if (args.length < 3) throw new Exception("Invalid count of arguments for entity");
            if (!this.objects.containsKey(args[0].trim())) throw new Exception("Id used in entities section was not defined " + args[0].trim());

            SavedGameObject obj = this.objects.get(args[0].trim());
            result.add((Entity)GameObjectFactory.getObject(obj.getObjectType(), new Vector2(Float.parseFloat(args[1].trim()), Float.parseFloat(args[2].trim())), obj.getTextureId()));
        }

        return result;
    }

    /**
     * Парсит файл сохранений
     * @param input 
     * @return файл мира, восстановленный из сохранения
     * @throws Exception Ошибки, при парсинге файла
     */
    public World parse(String input) throws Exception {
        String[] buffers = input.trim().split("\n");
        if (buffers.length == 0) throw new Exception("Empty file!");

        Token parameterName = null;
        String parameterValue = "";
        for (int i=0; i<buffers.length; i++)
        {
            String buffer = buffers[i].trim().replace("\r", "");
            if ("".equals(buffer)) continue;

            String[] tokens = buffer.split(":", -1);
            if (tokens.length > 2) throw new Exception("Invalid tokens count at row " + i);
            if (tokens.length == 1)
            {
                if (parameterName == null) throw new Exception("No parameter name was given at row " + i);
                parameterValue += "\n" + buffer;
                continue;
            }

            if (parameterName != null) parameters.put(parameterName, parameterValue);

            String parameterStringName = tokens[0].trim().toLowerCase();
            parameterName = Token.valueOf(parameterStringName);
            if (parameters.containsKey(parameterName)) throw new Exception("Parameter was reassigned at row " + i);
            parameterValue = tokens[1].trim();
        }

        parameters.put(parameterName, parameterValue);

        parseTextures();
        parseObjects();
        ArrayList<Tile> grid = parseMap();
        ArrayList<Entity> entities = parseEntities();

        World world = new World(grid, entities, Integer.parseInt(this.parameters.get(Token.map_width)), Integer.parseInt(this.parameters.get(Token.map_height)));
        return world;
    }
    
    /**
     * Парсит текущее состояние мира в файл
     * @param world мир
     * @param textures используемые в мире текстуры
     * @return строка, представляющая собой файл сохранения
     */
    public String parse(World world, HashMap<String, SavedTexture> textures)
    {
        StringBuilder result = new StringBuilder();
        // Дефолтные идишники, которые будут использоваться при объявлении объектов
        String[] ids = {
            "#", "_", "*", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "t", "u", "v", "w", "x",
            "y", "z"
        };

        // Сохранение текстур
        result.append("textures:").append(System.getProperty("line.separator"));
        for (Map.Entry<String, SavedTexture> entry : textures.entrySet())
        {
            String key = entry.getKey();
            SavedTexture value = entry.getValue();
            if (!"ui".equals(value.getAtlasName()))
            {
                result.append("    ").append(key).append(" = ")
                        .append(value.getTextureName()).append(" ")
                        .append(Integer.toString(value.getTextureType().getValue())).append(" ")
                        .append(value.getAtlasName()).append(System.getProperty("line.separator"));
            }
        }
        
        result.append("map_height: ").append(Integer.toString(world.getMap().getHeight())).append(System.getProperty("line.separator"))
                .append("map_width: ").append(Integer.toString(world.getMap().getWidth())).append(System.getProperty("line.separator"));
        
        ArrayList<SavedGameObject> objects = new ArrayList<SavedGameObject>();
        result.append("map:").append(System.getProperty("line.separator"));
        for (int j=world.getMap().getWidth()-1; j>=0; j--)
        {
            for (int i=0; i< world.getMap().getWidth(); i++)
            {
                Tile tile = world.getMap().getTileByCoordinates(i, j);
                boolean exists = false;
                int z;
                for (z=0; (z<objects.size()) && !exists; z++)
                    exists = (objects.get(z).getTextureId().equals(tile.getTextureId()) &&
                            objects.get(z).getObjectType() == tile.getType());
                
                if (!exists)
                {
                    objects.add(new SavedGameObject(tile.getTextureId(),tile.getType()));
                    z++;
                }
                
                result.append(ids[z-1]).append(" ");
            }
            result.append(System.getProperty("line.separator"));
        }
        
        result.append("entities:").append(System.getProperty("line.separator"));
        for (Entity entity : world.getMap().getEntities())
        {
            boolean exists = false;
            int z;
            for (z=0; (z<objects.size()) && !exists; z++)
                exists = (objects.get(z).getTextureId().equals(entity.getTextureId()) &&
                        objects.get(z).getObjectType() == entity.getType());

            if (!exists)
            {
                objects.add(new SavedGameObject(entity.getTextureId(),entity.getType()));
                z++;
            }

            result.append("    ").append(ids[z-1]).append(" ").append(entity.getPosition().x).append(" ").append(entity.getPosition().y).append(System.getProperty("line.separator"));
        }
        
        result.append("object_types:").append(System.getProperty("line.separator"));
        for (int i=0; i<objects.size(); i++)
        {
            result.append("    ").append(ids[i]).append(" = ").append(objects.get(i).getTextureId()).append(" ").append(objects.get(i).getObjectType().getValue()).append(System.getProperty("line.separator"));
        }
        return result.toString();
    }
    
    /**
     * Получить список текстур, используемых на загруженной карте
     * @return хешмап с текстурами и идентификаторами
    */
    public HashMap<String, SavedTexture> getTextureList() { return this.textures; };
}
