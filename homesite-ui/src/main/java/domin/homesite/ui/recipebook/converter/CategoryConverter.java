package domin.homesite.ui.recipebook.converter;

import domin.homesite.gil.domain.Category;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("categoryConverter")
public class CategoryConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s != null && s.trim().length() > 0) {
            String[] categoryData = s.split(",");
            String id = categoryData[0];
            String name = categoryData[1];
            return Category.builder().categoryId(id).categoryName(name).build();
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof Category) {
            Category category = (Category) o;
            return category.getCategoryId() + "," + category.getCategoryName();
        } else {
            return null;
        }
    }
}
