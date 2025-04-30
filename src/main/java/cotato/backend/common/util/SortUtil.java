package cotato.backend.common.util;

import org.springframework.data.domain.Sort;

import java.util.List;

public class SortUtil {
    public static Sort getSort(List<String> sort, List<String> direction) {
        Sort sortObj = Sort.unsorted();
        for (int i = 0; i < sort.size(); i++) {
            //정렬할 속성
            String property = sort.get(i);
            Sort.Direction dir = direction.get(i).equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            
            //정렬 객체 Order 생성
            Sort.Order order = new Sort.Order(dir, property);

            //정렬 객체 Order를 Sort 객체에 추가. Sort 객체내 Orders 리스트에 추가
            sortObj = sortObj.and(Sort.by(order));
        }

        return sortObj;
    }
}
