package domin.homesite.gil.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
/*
Setter nur fuer JSF verwenden
*/
@Getter
@Builder(toBuilder = true)
public class Instruction {

    private String instructionId;
    private String instructionText;

}
