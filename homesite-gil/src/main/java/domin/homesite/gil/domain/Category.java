package domin.homesite.gil.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Category {
    private String categoryId;
    private String categoryName;

}
