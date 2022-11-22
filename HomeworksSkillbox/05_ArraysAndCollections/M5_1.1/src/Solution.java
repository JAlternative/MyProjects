public class Solution{
    public static void main(String[] args) {
        String text = "Каждый охотник желает знать, где сидит фазан";
        String[] colors = text.split(",?\\s+");
        String[] colorsReturn = new String[colors.length];

//        for (int i = 0; i < colors.length; i++){
//            //System.out.println(colors[i]);
//        }
        for (int i = 0; i < colors.length; i++){
            System.out.println(colorsReturn[i] = colors[colors.length-1-i]);
        }


  }
}
