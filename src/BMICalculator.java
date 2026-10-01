public class BMICalculator {

    //Kilograms
    public double caculateMetric( double weight, double height ){
        return weight / (height * height) ;
    }

    //Pounds
    public double caculateImperial ( double weightLBS, double heightInches ){
        return (weightLBS * 703) / (heightInches * heightInches );
    }
}
