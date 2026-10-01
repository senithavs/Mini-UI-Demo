public class BMICategory {
    public static String getCategory(double bmi){
        if(bmi < 18.5){
            return "Underweight";
        }
        else if(bmi < 25.9){
            return "Normal";
        }
        else if(bmi < 29.9){
            return "Overweight";
        }
        else{
            return "Obese";
        }
    }
}
