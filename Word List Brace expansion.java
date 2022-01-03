//Timecomplexity:- O(m^n) [ where m is no of letters in brackets.
//Spacecomplexity :- O(output array size+ reccursion stack).

import java.util.*;
public class Main {
    public static void main(String[] args) {
        solution s= new solution();
        String S ="abcd";
        String[] array=s.expand(S);
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }

    public static class solution{
        public String[] expand(String S){
            List<String> output=new ArrayList<>();
            List<List<Character>> cache=new ArrayList<>();
            int i=0;
            while(i<S.length()){
                List<Character> temp=new ArrayList<>();
                if(S.charAt(i)=='{'){
                    i++;
                    while(S.charAt(i)!='}'){
                        if(S.charAt(i)!=','){ // initially given string is converted to list of lists for the data to do backtracking
                            temp.add(S.charAt(i));
                        }
                        i++;
                    }
                }
                else{
                    temp.add(S.charAt(i));
                }
                cache.add(temp);
                i++ ; 
            }
            backtracking(cache,output,0,new StringBuilder());
            String[] result=  output.toArray(new String[output.size()]);
            Arrays.sort(result); //the output is transeferd to array and sorted as it was asked lexicographically.
            return result;
        }
    
    private void backtracking(List<List<Character>> cache, List<String> output,int index, StringBuilder sb){
        if(index>=cache.size()){
            output.add(sb.toString());// when index goes out of bounds implies one brach until leaf has been travelled and upto
                                      // that extent string is added to output list.
            return;
        }
        for(char ch:cache.get(index)){
            sb.append(ch);// adding character is operation of backtracking.
            backtracking(cache,output,index+1,sb);
            sb.deleteCharAt(sb.length()-1);// removing last character is undoing operation of backtracking.
            
        }
        
    }
    }
}