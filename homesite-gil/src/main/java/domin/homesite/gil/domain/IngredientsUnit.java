package domin.homesite.gil.domain;

public enum IngredientsUnit {
    KILOGRAMM ("kg"),
    GRAMM ("g"),
    MILLILITER ("ml"),
    DEZILITER ("dl"),
    LITER ("l"),
    TEELOEFFEL ("TL"),
    ESSLOEFFEL ("EL"),
    ETWAS ("etwas"),
    WENIG ("wenig"),
    PRISE ("eine Prise"),
    STUECK ("Stk.");

    public final String value;

    IngredientsUnit(String value) {this.value = value; }

    public static IngredientsUnit fromValue(String value){
        for(IngredientsUnit unit : values()){
            if(unit.value.equals(value)){
                return unit;
            }
        }
        throw new IllegalArgumentException(value);
    }
}
