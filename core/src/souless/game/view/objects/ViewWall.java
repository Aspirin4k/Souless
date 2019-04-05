package souless.game.view.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import souless.game.model.ResourcesManager;
import souless.game.objects.GameObject;
import souless.game.objects.GameObjectFactory;
import souless.game.objects.Tile;

import java.util.ArrayList;

public class ViewWall extends ViewObject {

    public ViewWall(GameObject tile) {
        super(tile);
    }

    @Override
    public void generateTexture(ArrayList<Tile> adjancedTiles, ResourcesManager resManager, SpriteBatch batch) {
        // Соседние ячейки имеют следующие индексы (Разряд бита - 1 - пусто, 0 - стена)
        // 416
        // 0#2
        // 537
        byte[] p = {4, 1, 6, 0, 2, 5, 3, 7};
        // Можно представить байтом (инт16, т.к. нужно положительное число от 0 до 255)
        // Массив булей необходим для более простого построения текстуры
        // Инт16 для определения индекса текстуры
        short hashvalue=0;
        boolean[] bufferBoolean = new boolean[8];
        for (int i=0; i<8; i++)
        {
            // Истина, если нет стены
            bufferBoolean[p[i]] = !((adjancedTiles.get(i) == null) 
                    || (adjancedTiles.get(i).getType() == GameObjectFactory.ObjectType.WALL));
            
            if (bufferBoolean[p[i]])
            {
                hashvalue += Math.pow(2, 7-p[i]);
            }
        }
                
        // Определение индекса текстуры
        // TODO: Подумать, как сделать иначе
        int index=0;
        switch (hashvalue/16)
        {
            case 15:
            case 14:
            case 13:
            case 11:
            case 10:
            case 7:
            case 5:
            {
                // 1 вариант
                index = hashvalue / 16;
                break;
                // Индексы 5,7,10,11,13,14,15
            }
            case 12:    // 1-й бит
            case 9:     // 3-й бит
            case 6:     // 2-й бит
            case 3:     // 4-й бит
            {
                // 2 варианта
                int j = hashvalue / 48;
                if ((hashvalue >> (4-j)) % 2 == 1)
                    index = hashvalue / 16 + 16;
                else
                    index = hashvalue / 16;
                break;
                // Индексы 3, 6, 9, 12, 19, 22, 25, 28
            }
            case 8:
            {
                index = hashvalue / 16 + (hashvalue % 4)*16;
                break;
                // Индексы 8, 24, 40, 56
            }
            case 4:
            {
                index = hashvalue / 16 + (((hashvalue/4)%2)*2 + hashvalue % 2)*16;
                break;
                // Индексы 4, 20, 36, 52
            }
            case 2:
            {
                index = hashvalue / 16 + ((hashvalue / 4) % 4)*16;
                break;
                // Индексы 2, 18, 34, 50
            }
            case 1:
            {
                index = hashvalue / 16 + (((hashvalue/8)%2)*2 + (hashvalue / 2) % 2)*16;
                break;
                // Индексы 1, 17, 33, 49
            }
            case 0:
            {
                // 16 вариантов
                index = hashvalue + 57;
                break;
                // Индексы 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72
            }
        }

        index += 20;
        // Пытаемся загрузить текстуру под данным индексом. если безуспешно, то создаем ее
        this.texture = resManager.getTexture(linkedTile.getTextureId(), index);
        if (this.texture !=null) return;
        
        Matrix4 matrix = new Matrix4();
        matrix.setToOrtho2D(0, 0, 64, 64);
        TextureRegion tr;
        
        FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888,64,64,false);
        
        frameBuffer.begin();
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.setProjectionMatrix(matrix);

        // Начинается, собственно, рендер
        batch.begin();
        
        if (bufferBoolean[1])
        {
            tr = resManager.getTexture(linkedTile.getTextureId(), 3);
            tr.flip(false, true);
            batch.draw(tr, 0, 0, 64, 64);
            tr.flip(false, true);
        }

        if (bufferBoolean[3])
        {
            tr = resManager.getTexture(linkedTile.getTextureId(), 7);
            tr.flip(false, true);
            batch.draw(tr, 0, 0, 64, 64);
            tr.flip(false, true);
        }
        
        // Проверка, если слева стена
        if (bufferBoolean[0])
        {
            if (bufferBoolean[1])
            {
                tr = resManager.getTexture(linkedTile.getTextureId(), 5);
                tr.flip(false, true);
                batch.draw(tr, 0, 0, 64, 64);
                tr.flip(false, true);
            }
            else
            {
                tr = resManager.getTexture(linkedTile.getTextureId(), 6);
                tr.flip(false, true);
                batch.draw(tr, 0, 0, 64, 64);
                tr.flip(false, true);
            }

            if (bufferBoolean[3])
            {
                tr = resManager.getTexture(linkedTile.getTextureId(), 8);
                tr.flip(false, true);
                batch.draw(tr, 0, 0, 64, 64);
                tr.flip(false, true);
            }
            else
            {
                tr = resManager.getTexture(linkedTile.getTextureId(), 4);
                tr.flip(false, true);
                batch.draw(tr, 0, 0, 64, 64);
                tr.flip(false, true);
            }
        }
        else
        {
            if (bufferBoolean[1])
            {
                tr = resManager.getTexture(linkedTile.getTextureId(), 10);
                tr.flip(false, true);
                batch.draw(tr, 0, 0, 64, 64);
                tr.flip(false, true);
            }
            else
            {
                if (bufferBoolean[4])
                {
                    tr = resManager.getTexture(linkedTile.getTextureId(), 2);
                tr.flip(false, true);
                batch.draw(tr, 0, 0, 64, 64);
                tr.flip(false, true);
                }
            }

            if (bufferBoolean[3])
            {
                tr = resManager.getTexture(linkedTile.getTextureId(), 9);
                tr.flip(false, true);
                batch.draw(tr, 0, 0, 64, 64);
                tr.flip(false, true);
            }
            else
            {
                if (bufferBoolean[5])
                {
                    tr = resManager.getTexture(linkedTile.getTextureId(), 1);
                    tr.flip(false, true);
                    batch.draw(tr, 0, 0, 64, 64);
                    tr.flip(false, true);
                }
            }
        }
        
        // Проверка на стену справа. аналогично, за исключением отзеракливания текстур
        if (bufferBoolean[2])
        {
            if (bufferBoolean[1])
            {
                tr = resManager.getTexture(linkedTile.getTextureId(), 5);
                tr.flip(true, true);
                batch.draw(tr, 0, 0, 64, 64);
                tr.flip(true, true);
            }
            else
            {
                tr = resManager.getTexture(linkedTile.getTextureId(), 6);
                tr.flip(true, true);
                batch.draw(tr, 0, 0, 64, 64);
                tr.flip(true, true);
            }

            if (bufferBoolean[3])
            {
                tr = resManager.getTexture(linkedTile.getTextureId(), 8);
                tr.flip(true, true);
                batch.draw(tr, 0, 0, 64, 64);
                tr.flip(true, true);
            }
            else
            {
                tr = resManager.getTexture(linkedTile.getTextureId(), 4);
                tr.flip(true, true);
                batch.draw(tr, 0, 0, 64, 64);
                tr.flip(true, true);
            }
        }
        else
        {
            if (bufferBoolean[1])
            {
                tr = resManager.getTexture(linkedTile.getTextureId(), 10);
                tr.flip(true, true);
                batch.draw(tr, 0, 0, 64, 64);
                tr.flip(true, true);
            }
            else
            {
                if (bufferBoolean[6])
                {
                    tr = resManager.getTexture(linkedTile.getTextureId(), 2);
                    tr.flip(true, true);
                    batch.draw(tr, 0, 0, 64, 64);
                    tr.flip(true, true);
                }
            }

            if (bufferBoolean[3])
            {
                tr = resManager.getTexture(linkedTile.getTextureId(), 9);
                tr.flip(true, true);
                batch.draw(tr, 0, 0, 64, 64);
                tr.flip(true, true);
            }
            else
            {
                if (bufferBoolean[7])
                {
                    tr = resManager.getTexture(linkedTile.getTextureId(), 1);
                    tr.flip(true, true);
                    batch.draw(tr, 0, 0, 64, 64);
                    tr.flip(true, true);
                }
            }
        }
        
        batch.end();

        frameBuffer.end();
        
        texture = new AtlasRegion(frameBuffer.getColorBufferTexture(),0,0,64,64);
        resManager.loadDynamicTexture(frameBuffer.getColorBufferTexture(), linkedTile.getTextureId(), index);
        texture = resManager.getTexture(linkedTile.getTextureId(), index);
    }
    
}
