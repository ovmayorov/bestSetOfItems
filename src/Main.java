import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Item> items = new ArrayList<>();
        items.add( new Item(356.4, 10 ));
        items.add( new Item(13.75, 40 ));
        items.add( new Item(136.63, 70 ));
    //    items.add( new Item(93.22, 75 ));
        items.add( new Item(13, 300 ));
        items.add( new Item(130, 200 ));


        double maxWeight = 200;
        System.out.println("Max Cost = "+findMaxCost(items,maxWeight));
        System.out.println("-----------------------------------------");
        //ДЗ = Выводим в терминал коллекцию вещей с большей общей стоимостью и не превышающую максимальный вес.
        System.out.println("Best set of items: "+ findBestSetOfItems(items,maxWeight));
        System.out.println("-----------------------------------------");
        //ДЗ = Вывод на экран всех комбинаций
        //System.out.println("All combinations of sets:\n"+getAllSubsets(items));
        List<List<Item>> allSet = getAllSubsets(items);
        System.out.println("All combinations of sets:");
        for(int i=0; i< allSet.size(); i++){
            System.out.println(allSet.get(i));
        }
    }

    ////////////////////////////////////////////////////////////
    //Есть список вещей, есть ограничение по максимальному весу. Определить максимальную суммарную стоимость награбленного
    public static double findMaxCost(List<Item> items, double maxWeight){
        double maxCost = 0;
        //данный for для проходу по маске, то есть по каждому варианту сочетаний элементов в коллекции.
        for(int mask = 0; mask < (1<<items.size()); mask++){
            //На данной итерации мы имеем какой-то набор вещей.
            // Для этого набора можем определить стоимость и вес, Пройдясь по данному набору опять циклом for
            double totalCost = 0;      //Суммарная стоимость
            double totalWeight = 0;    //Суммарный вес
            for(int index = 0; index < items.size(); index++){
                //делая & маски с 1 мы определяем, берем элемент коллекции в данный набор или нет.
                //маску сдвигаем вправо на index, так проверяем все элементы маски в сочетании с элементами коллекции
                int value = (mask >>index) & 1;
                if(value == 1){ //каждый байт маски соответствует элементу коллекции.
                    // Если байт маски равен 1, то мы берем элемент коллекции в оборот
                    //соответственно подсчитываем общую стоимость и вес.
                    totalCost += items.get(index).getCost();
                    totalWeight += items.get(index).getWeight();
                }
            }  //конец цикла работы с элементами коллекции
            //Проверяем, не вышли ли мы за лимит веса. Если общий вес меньше или равен максимально допустимому
            if(totalWeight<=maxWeight){
                //проверяем, чтобы общая цена была выше всех предыдущих, тогда сохраняем maxCost
                maxCost = Math.max(maxCost, totalCost);

//                if(maxCost<totalCost) {
//                    maxCost = totalCost;
//                }
            }

        } //конец цикла определения маски

        return maxCost;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Домашнее задание.
    //1.	public static List<Item> findBestSetOfItems(double maxWeight) – метод должен возвращать
    // список (используйте arrayList) объектов класса Item, суммарная стоимость которых максимальна,
    // при этом не превышает maxWeight.
    public static List<Item> findBestSetOfItems(List<Item> items, double maxWeight){
        double maxCost = 0;
        ArrayList<Item> myBestSetOfItems = new ArrayList<>(); //это мы будем возвращать
        for(int mask = 0; mask<(1<<items.size()); mask++){
            double totalCost = 0;      //Суммарная стоимость
            double totalWeight = 0;    //Суммарный вес
            for(int index = 0; index < items.size(); index++){
                //делая & маски с 1 мы определяем, берем элемент коллекции в данный набор или нет.
                //маску сдвигаем вправо на index, так проверяем все элементы маски в сочетании с элементами коллекции
                int value = (mask >>index) & 1;
                if(value == 1){ //каждый байт маски соответствует элементу коллекции.
                    // Если байт маски равен 1, то мы берем элемент коллекции в оборот
                    //соответственно подсчитываем общую стоимость и вес.
                    totalCost += items.get(index).getCost();
                    totalWeight += items.get(index).getWeight();
                }
            }  //конец цикла работы с элементами коллекции
            if(totalWeight<maxWeight){
                if(totalCost>maxCost){
                    if(!myBestSetOfItems.isEmpty()){
                        myBestSetOfItems.clear();
                    }
                    for(int index = 0; index< items.size(); index++){
                        int value = (mask>>index)&1;
                        if(value == 1){
                            myBestSetOfItems.add(new Item(items.get(index).getCost(),items.get(index).getWeight() ));
                        }
                    }
                }
            }
        }
        return myBestSetOfItems;
    }

    //2.	public static List<List<Item>> getAllSubsets(List<Item> items) – метод возвращает всевозможные комбинации
    // наших вещей в виде списка списков. Обратите внимание: List<List<Item> означает список, который хранит списки,
    // которые хранят Item. Т.е мы имеем что-то такое:
    //{{}, {item1}, {item1, item2}, {item2}, {item3}, {item1, item2, item3}, {item1, item3}}.

    public static List<List<Item>> getAllSubsets(List<Item> items){
        List<List<Item>> allSubsets = new ArrayList<List<Item>>();

    //Проходимся по всем комбинациям элементов коллекции
        for(int mask =0; mask<(1<<items.size()); mask++){
            List<Item> myTemp = new ArrayList<>();
            for(int index = 0; index<items.size();index++){
                int value = (mask>>index) & 1;
                if(value == 1){
                    myTemp.add(new Item(items.get(index).getCost(), items.get(index).getWeight()));
                }
            }//end of for internal for current set
            allSubsets.add(myTemp);
        }//end of for with mask
        return allSubsets;
    }


}