/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.util.Scanner;

/**
 *
 * @author Marcos
 * @param <TEnum>
 */
public class GeneralMenu<TEnum> {
    private String _title = "";
    private String[] _descriptions = null;
    private TEnum[] _optionList = null;
    
    
    public GeneralMenu(String title,TEnum[] optionList,String[] descriptions){
        _title = title;
        _optionList = optionList;
        if(descriptions.length == _optionList.length){
            _descriptions = descriptions;
        }else{
            _descriptions = null;
        }
    }
            
    public void runMenu(){
        String split1 = "*********************";
        String split2 = "----------------";
        System.out.println(split2);
        System.out.println(_title);
        System.out.println(split2);
        System.out.println(split1);
        int i;
        for(TEnum o: _optionList){
            i=((Enum)o).ordinal();
            System.out.println("   "+Integer.toString(i+1)+". "+_descriptions[i]);
        }
        System.out.println(split1);
    }
    
    public TEnum readOption(Scanner sc){
        TEnum op = null;
        int i = -1;
        do{
            System.out.print("Select an option --> ");
            i = sc.nextInt();
            if (i > 0 && i <= _optionList.length) {
                op = _optionList[i - 1];
                continue;
            }
            System.out.println("That number is not listed. Please, give a listed number.");
        }while(op == null);
        return op;
    }
        
}