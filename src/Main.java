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
    //���� ������ �����, ���� ����������� �� ������������� ����. ���������� ������������ ��������� ��������� �������������
    public static double findMaxCost(List<Item> items, double maxWeight){
        double maxCost = 0;
        //������ for ��� ������� �� �����, �� ���� �� ������� �������� ��������� ��������� � ���������.
        for(int mask = 0; mask < (1<<items.size()); mask++){
            //�� ������ �������� �� ����� �����-�� ����� �����.
            // ��� ����� ������ ����� ���������� ��������� � ���, �������� �� ������� ������ ����� ������ for
            double totalCost = 0;      //��������� ���������
            double totalWeight = 0;    //��������� ���
            for(int index = 0; index < items.size(); index++){
                //����� & ����� � 1 �� ����������, ����� ������� ��������� � ������ ����� ��� ���.
                //����� �������� ������ �� index, ��� ��������� ��� �������� ����� � ��������� � ���������� ���������
                int value = (mask >>index) & 1;
                if(value == 1){ //������ ���� ����� ������������� �������� ���������.
                    // ���� ���� ����� ����� 1, �� �� ����� ������� ��������� � ������
                    //�������������� ������������ ����� ��������� � ���.
                    totalCost += items.get(index).getCost();
                    totalWeight += items.get(index).getWeight();
                }
            }  //����� ����� ������ � ���������� ���������
            //���������, �� ����� �� �� �� ����� ����. ���� ����� ��� ������ ��� ����� ����������� �����������
            if(totalWeight<=maxWeight){
                //���������, ����� ����� ���� ���� ���� ���� ����������, ����� ��������� maxCost
                maxCost = Math.max(maxCost, totalCost);

//                if(maxCost<totalCost) {
//                    maxCost = totalCost;
//                }
            }

        } //����� ����� ����������� �����

        return maxCost;
    }


}