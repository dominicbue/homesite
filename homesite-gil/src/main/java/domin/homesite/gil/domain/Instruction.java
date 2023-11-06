package domin.homesite.gil.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Instruction {

    private String instructionId;
    private String instructionText;

}
