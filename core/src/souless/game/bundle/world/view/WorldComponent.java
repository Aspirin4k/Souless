package souless.game.bundle.world.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import souless.game.bundle.world.map.view.TileLayerRenderer;
import souless.game.model.ResourceManager;
import souless.game.bundle.world.IWorldUploadListener;
import souless.game.bundle.world.entity.World;
import souless.game.objects.GameObject;
import souless.game.view.IComponent;
import souless.game.view.objects.ViewWorld;

public class WorldComponent implements IComponent, IWorldUploadListener {
    public static float CAMERA_WIDTH;
    public static float CAMERA_HEIGHT;

    private final float UI_Y_COORDINATE = 2;

    private final ResourceManager resourceManager;
    private final Batch batch;
    private final TileLayerRenderer tileLayerRenderer;

    private World world;

    // Содержим информацию о мире
    private ViewWorld viewWorld;

    // Все, что надо для отрисовки
    private Sprite sprite;

    // Игровая камера
    private OrthographicCamera camera;
    private Viewport viewport;
    private GameObject camerasTarget;

    // Камера интерфейса
    private OrthographicCamera uiCamera;
    private Viewport uiViewport;

    float stateTime;

    public WorldComponent(
            ResourceManager resourceManager,
            Batch batch,
            TileLayerRenderer tileLayerRenderer
    )  {
        this.resourceManager = resourceManager;
        this.batch = batch;
        this.tileLayerRenderer = tileLayerRenderer;
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

    public void onUploadWorld(World world) {
        CAMERA_WIDTH = 25f;
        CAMERA_HEIGHT = 10f;

        // Принадлежности для рисования
        this.sprite = new Sprite();

        this.world = world;

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
//        camerasTarget = world.getPlayer();
    }

    @Override
    public void render()
    {
        if (null != this.world) {
            this.renderWorld();
//            stateTime += Gdx.graphics.getDeltaTime();
//
////            refreshCameraCoordinates();
//
//            this.camera.update();
////            this.batch.setProjectionMatrix(this.camera.combined);
//            this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//
//            this.batch.begin();
//
////            viewWorld.draw((SpriteBatch)this.batch, sprite, UI_Y_COORDINATE, camera, stateTime);
//
//            this.batch.end();
//
//            this.uiCamera.update();
//            this.batch.setProjectionMatrix(this.uiCamera.combined);
////        this.font.setColor(Color.BLACK);
//
//            this.batch.begin();
//
////        drawConsoleLog();
//
//            this.batch.end();
        }
    }

    private void renderWorld()
    {
        this.batch.begin();
        this.tileLayerRenderer.draw(this.world.getTileMap());
        this.batch.end();
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
        if (newX + this.camera.viewportWidth / 2 > this.world.getMap().getWidth())
            newX = this.world.getMap().getWidth() - this.camera.viewportWidth / 2;

        // Если камера выступает за пределы нижей границы карты
        if (newY - this.camera.viewportHeight / 2 < 0)
            newY = this.camera.viewportHeight / 2;

        // Если камера выступает за пределы верхней границы карты
        if (newY + this.camera.viewportHeight / 2 - UI_Y_COORDINATE > this.world.getMap().getHeight())
            newY = this.world.getMap().getHeight() - this.camera.viewportHeight / 2 + UI_Y_COORDINATE;

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
}
