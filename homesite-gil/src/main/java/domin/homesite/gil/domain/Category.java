package domin.homesite.gil.domain;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder(toBuilder = true)
public class Category implements Serializable {
    private String categoryId;
    private String categoryName;

}
