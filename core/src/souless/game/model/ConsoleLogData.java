package souless.game.model;

import java.util.ArrayList;
import java.util.Observable;

public class ConsoleLogData extends Observable {
    
    // Коллекция сообщений, отсортированная от самых новых до старых
    private final ArrayList<String> collection;
    
    /**
     * Стандартный конструктор. Инициализирует параметры шрифта
     */
    public ConsoleLogData()
    {
        super();
        collection = new ArrayList<String>();
    }
    
    /**
     * Добавляет новое сообщение в лог консоли.
     * При этом, сообщение делится на строки, каждая из которых меньше указанной
     * длины в пикселях
     * @param msg сообщение
     */
    public void add(String msg)
    {
        collection.add(0,msg);
        
        setChanged();
        notifyObservers();
    }  
    
    /**
     * Возвращает ссылку на коллекцию сообщений
     * @return 
     */
    public ArrayList<String> getMessages() { return collection; }
}
