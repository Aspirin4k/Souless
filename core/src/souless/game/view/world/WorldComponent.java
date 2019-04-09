package souless.game.view.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import souless.game.model.ResourcesManager;
import souless.game.model.world.IWorldUploadListener;
import souless.game.model.world.entity.WorldResource;
import souless.game.objects.GameObject;
import souless.game.view.ConsoleLog;
import souless.game.view.IComponent;
import souless.game.view.objects.ViewWorld;

public class WorldComponent implements IComponent, IWorldUploadListener {
    public static float CAMERA_WIDTH;
    public static float CAMERA_HEIGHT;

    private final float UI_Y_COORDINATE = 2;
    private final float CONSOLE_WIDTH = 12;

    // Содержим информацию о мире
    private ViewWorld viewWorld;
    private WorldResource GameWorld;
    private ResourcesManager resManager;
    private ConsoleLog log;

    // Все, что надо для отрисовки
    private SpriteBatch batch;
    private Sprite sprite;
    private BitmapFont font;

    // Игровая камера
    private OrthographicCamera camera;
    private Viewport viewport;
    private GameObject camerasTarget;

    // Камера интерфейса
    private OrthographicCamera uiCamera;
    private Viewport uiViewport;

    float stateTime;

    public WorldComponent(ResourcesManager rM)
    {
        this.resManager = rM;
        // Отображение мира в зависимости от размера экрана
//        switch (Gdx.graphics.getWidth())
//        {
//            case 2560:
//            {
//                CAMERA_WIDTH = 21f;
//                CAMERA_HEIGHT = 9f;
//                break;
//            }
//            case 1920:
//            {
//                CAMERA_WIDTH = 16f;
//                CAMERA_HEIGHT = 9f;
//                break;
//            }
//        }


    }

    public void onUploadWorld(WorldResource world) {
        CAMERA_WIDTH = 25f;
        CAMERA_HEIGHT = 10f;

        // Принадлежности для рисования
        this.batch = new SpriteBatch();
        this.font = this.resManager.getFont();
        this.sprite = new Sprite();

        // Мир и интерфейс
        this.viewWorld = new ViewWorld(world, this.resManager, batch);
        this.GameWorld = world;

        // Игровая камера
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(CAMERA_WIDTH, CAMERA_HEIGHT, this.camera);
        this.viewport.apply();

        // Камера для отрисовки интерфейса
        this.uiCamera = new OrthographicCamera();
        this.uiViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), this.uiCamera);
        this.uiViewport.apply();

        this.uiCamera.position.set(this.uiCamera.viewportWidth / 2, this.uiCamera.viewportHeight / 2, 0);

        // Представление консоли, которая выводит игровые сообщения
//        log = new ConsoleLog(CONSOLE_WIDTH * this.uiViewport.getWorldWidth() / CAMERA_WIDTH,
//                UI_Y_COORDINATE * this.uiViewport.getWorldHeight() / CAMERA_HEIGHT, font,
//                rM.getLogData());
//        log.updateConsole();

        stateTime = 0f;
        camerasTarget = world.getPlayer();
    }

    @Override
    public void render()
    {
        if (null != this.GameWorld) {
            stateTime += Gdx.graphics.getDeltaTime();

            refreshCameraCoordinates();

            this.camera.update();
            this.batch.setProjectionMatrix(this.camera.combined);
            this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            this.batch.begin();

            viewWorld.draw(batch, sprite, UI_Y_COORDINATE, camera, stateTime);

            this.batch.end();

            this.uiCamera.update();
            this.batch.setProjectionMatrix(this.uiCamera.combined);
//        this.font.setColor(Color.BLACK);

            this.batch.begin();

//        drawConsoleLog();

            this.batch.end();
        }
    }

    public void drawConsoleLog()
    {
        this.sprite = new Sprite(this.resManager.getTexture("CONSOLE_LOG"));
        this.sprite.setPosition(log.getPosition().x, log.getPosition().y);
        this.sprite.setSize(log.getSize().x, log.getSize().y);
        this.sprite.draw(this.batch);
        log.draw(batch);
    }

    /**
     * Переместить камеру на игрока
     */
    public void refreshCameraCoordinates()
    {
        float newX;
        float newY;

        // Фокусируется на игроке
        newX = this.camerasTarget.getPosition().x + 0.5f;
        newY = this.camerasTarget.getPosition().y + 2.0f;

        // Если камера выступает за пределы левой границы карты
        if (newX - this.camera.viewportWidth / 2 < 0)
            newX = this.camera.viewportWidth / 2;

        // Если камера выступает за пределы правой границы карты
        if (newX + this.camera.viewportWidth / 2 > this.GameWorld.getMap().getWidth())
            newX = this.GameWorld.getMap().getWidth() - this.camera.viewportWidth / 2;

        // Если камера выступает за пределы нижей границы карты
        if (newY - this.camera.viewportHeight / 2 < 0)
            newY = this.camera.viewportHeight / 2;

        // Если камера выступает за пределы верхней границы карты
        if (newY + this.camera.viewportHeight / 2 - UI_Y_COORDINATE > this.GameWorld.getMap().getHeight())
            newY = this.GameWorld.getMap().getHeight() - this.camera.viewportHeight / 2 + UI_Y_COORDINATE;

        this.camera.position.x += (newX - this.camera.position.x) * 0.1f;
        this.camera.position.y += (newY - this.camera.position.y) * 0.1f;
    }

    public void resize(int width, int height)
    {
//        if ((float)Gdx.graphics.getWidth() / Gdx.graphics.getHeight() == 21f / 9f)
//        {
//            CAMERA_WIDTH = 21f;
//            CAMERA_HEIGHT = 9f;
//        }
//
//        if ((float)Gdx.graphics.getWidth() / Gdx.graphics.getHeight() == 16f / 9f)
//        {
//            CAMERA_WIDTH = 16f;
//            CAMERA_HEIGHT = 9f;
//        }

        this.viewport.update(width, height);
        refreshCameraCoordinates();

        this.uiViewport.update(width, height);

        this.uiCamera.position.set(this.uiCamera.viewportWidth / 2, this.uiCamera.viewportHeight / 2, 0);
    }

    /**
     * Устанавливает слежение камеры за соответствующим объектом
     * @param obj
     */
    public void setCamerasTarget(GameObject obj)
    {

    }

    /**
     * Позволяет пролистать консоль на несколько позиций вверх или вниз
     * @param pos
     */
    public void listConsole(int pos) { this.log.listConsole(pos); }
}
