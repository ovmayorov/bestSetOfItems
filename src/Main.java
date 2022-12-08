import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Item> items = new ArrayList<>();
        items.add( new Item(356.4, 10 ));
        items.add( new Item(13.75, 40 ));
        items.add( new Item(136.63, 70 ));
        items.add( new Item(13, 300 ));
        items.add( new Item(130, 200 ));

        double maxWeight = 200;
        System.out.println("Max Cost = "+findMaxCost(items,maxWeight));


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


}