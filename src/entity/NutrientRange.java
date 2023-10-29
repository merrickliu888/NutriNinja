package entity;

public class NutrientRange {
    private Range calorieRange;
    private Range fatRange;
    private Range proteinRange;
    private Range carbRange;

    public NutrientRange(Range calorieRange, Range fatRange, Range proteinRange, Range carbRange) {
        this.calorieRange = calorieRange;
        this.fatRange = fatRange;
        this.proteinRange = proteinRange;
        this.carbRange = carbRange;
    }

    public Range getCalorieRange() {
        return calorieRange;
    }

    public Range getFatRange() {
        return fatRange;
    }

    public Range getProteinRange() {
        return proteinRange;
    }

    public Range getCarbRange() {
        return carbRange;
    }
}