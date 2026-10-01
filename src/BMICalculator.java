public class BMICalculator {

    //Kilograms
    public double calculateMetric( double weight, double height ){
        return weight / (height * height) ;
    }

    //Pounds
    public double calculateImperial ( double weightLBS, double heightInches ){
        return (weightLBS * 703) / (heightInches * heightInches );
    }
}
