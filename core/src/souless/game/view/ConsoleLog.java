package souless.game.view;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import souless.game.model.ConsoleLogData;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ConsoleLog implements Observer {
    private final Rectangle POSITION;
    
    private final BitmapFont font;
    
    // Координаты отступа
    private final float LEFT_PADDING = 64;
    private final float RIGHT_PADDING = 64;
    private final float DOWN_PADDING = 46;
    private final float UP_PADDING = 16;
    private final int LINES_IN_CONSOLE;
    
    private final float actualTextWidth;
    private final float actualTextHeight;
    
    // Коллекция содержит строки, которые отрисовываются в консоли в текущий момент
    private final ArrayList<String> LinesToRender;
    
    private final ConsoleLogData logData;
    private int CurrentRenderPosition;
    
    public ConsoleLog(float width, float height, BitmapFont font, ConsoleLogData logData)
    {
        // Установка позиции элемента для рендера
        POSITION = new Rectangle(0,0,width,height);
        
        // Установка области, в которой будет отрисован текст
        actualTextWidth = width - LEFT_PADDING - RIGHT_PADDING;
        actualTextHeight = height - UP_PADDING - DOWN_PADDING;
        
        // Установка шрифта и вычисление количества строк для рендера
        this.font = font;
        LINES_IN_CONSOLE = Math.round(actualTextHeight / font.getLineHeight());
        
        // Установка дефолтного значения вывода строк
        CurrentRenderPosition=0;
        // Создание массива выводимых строк
        LinesToRender = new ArrayList<String>();
        // Установка ссылки на данные консоли. Передача ссылки на шрифт и размера области вывода
        this.logData = logData;
        
        this.logData.addObserver(this);
    }
    
    /**
     * Отрисовка текста
     * @param batch 
     */
    public void draw(SpriteBatch batch)
    {
        for (int i=0; i< LinesToRender.size(); i++)
            font.draw(batch, LinesToRender.get(i), 
                    POSITION.x + LEFT_PADDING,
                    POSITION.y + font.getLineHeight()*i + DOWN_PADDING);
    }
    
    public Vector2 getPosition() { return new Vector2(POSITION.x, POSITION.y); }
    public Vector2 getSize()     { return new Vector2(POSITION.width, POSITION.height); }
    
    /**
     * Обновляет содержимое для рендера консоли
     */
    public void updateConsole()
    {
        LinesToRender.clear();
        
        int i=0;            // Индекс сообщения из модели
        int CurrentRow=0;   // Строка в консоли из представления
        
        GlyphLayout layout = new GlyphLayout();
        
        while ((CurrentRow < CurrentRenderPosition + LINES_IN_CONSOLE) && (i<logData.getMessages().size()))
        {
            ArrayList<String> bufferList = new ArrayList<String>();
            // Получаем очередное сообщение
            String msg = logData.getMessages().get(i);
            layout.setText(font, msg);
            
            while (layout.width > actualTextWidth)
            {
                String bufferString="";
                layout.setText(font, bufferString);
                int len = 0;
                int lastSpace=-1;
                while(layout.width < actualTextWidth)
                {
                    bufferString += msg.charAt(len);
                    if (bufferString.charAt(len) == ' ') 
                        lastSpace = len;
                    len++;
                    layout.setText(font, bufferString);
                }
            
                // Если в строке не было пробелов, то придется обрезать
                bufferList.add(0,bufferString.substring(0,bufferString.length()-1));
                if (lastSpace == -1)
                {
                    msg = msg.substring(len-1,msg.length());
                }
                else
                {
                    msg = msg.substring(lastSpace+1, msg.length());
                }

                layout.setText(font, msg);
            }

            // Случай, если просто закончилось сообщение
            bufferList.add(0,msg);
            
            if (CurrentRow + bufferList.size()  > CurrentRenderPosition)
            {
                int iteratorBuffer = CurrentRenderPosition - CurrentRow;
                if (iteratorBuffer < 0) iteratorBuffer = 0;
                for (int j=0; 
                        (iteratorBuffer + j < bufferList.size()) &&
                        (LinesToRender.size() < LINES_IN_CONSOLE); j++)
                {
                    LinesToRender.add(bufferList.get(iteratorBuffer + j));
                }
            }
            
            CurrentRow += bufferList.size();
                
            i++;
        }
        
        if (LinesToRender.size() < LINES_IN_CONSOLE)
        {
            CurrentRenderPosition = CurrentRow - LINES_IN_CONSOLE;
            if (CurrentRenderPosition < 0) CurrentRenderPosition = 0;
        }
        
    }
    
    /**
     * Пролистать консоль
     * @param pos насколько позиций
     */
    public void listConsole(int pos)
    {
        // Этот случай происходит тогда, когда консоль пролистана максимально вверх
        if ((pos > 0) && (LinesToRender.size()<LINES_IN_CONSOLE))
            return;
        
        CurrentRenderPosition += pos;
        
        if (CurrentRenderPosition < 0)
            CurrentRenderPosition = 0;
        
        updateConsole();
    }

    @Override
    public void update(Observable o, Object arg) {
        CurrentRenderPosition = 0;
        updateConsole();
    }
}
