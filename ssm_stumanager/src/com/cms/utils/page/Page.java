// 
// 
// 

package com.cms.utils.page;

import java.util.List;

public interface Page<E> extends Iterable<E>
{
    int getFirstPageNum();
    
    int getLastPageNum();
    
    int getPageNum();
    
    int getPageSize();
    
    List<E> getItems();
    
    boolean isFirstPage();
    
    boolean isLastPage();
    
    boolean isEmpty();
}
