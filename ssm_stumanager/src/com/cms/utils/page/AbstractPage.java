// 
// 
// 

package com.cms.utils.page;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.List;

public abstract class AbstractPage<E> implements Page<E>
{
    public static final int DEFAULT_FIRST_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;
    protected int pageSize;
    protected int pageNum;
    protected List<E> items;
    protected boolean lastPage;
    protected boolean firstPage;
    
    public AbstractPage() {
        this.pageSize = 10;
        this.pageNum = 1;
    }
    
    @Override
    public int getFirstPageNum() {
        return 1;
    }
    
    @Override
    public int getPageSize() {
        return this.pageSize;
    }
    
    public int getPageSizeMax() {
        return 8;
    }
    
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }
    
    @Override
    public int getPageNum() {
        return this.pageNum;
    }
    
    public void setPageNum(int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        this.pageNum = pageNum;
    }
    
    @Override
    public List<E> getItems() {
        return this.items;
    }
    
    public void setItems(Collection<E> items) {
        if (items == null) {
            items = (Collection<E>)Collections.emptyList();
        }
        this.items = new ArrayList<E>((Collection<? extends E>)items);
        this.lastPage = (this.items.size() < this.pageSize);
        this.firstPage = (this.getPageNum() <= this.getFirstPageNum());
    }
    
    @Override
    public boolean isFirstPage() {
        return this.firstPage = (this.getPageNum() <= this.getFirstPageNum());
    }
    
    @Override
    public boolean isLastPage() {
        return this.lastPage;
    }
    
    public int getPrevPageNum() {
        return this.isFirstPage() ? this.getFirstPageNum() : (this.getPageNum() - 1);
    }
    
    public int getNextPageNum() {
        return this.isLastPage() ? this.getPageNum() : (this.getPageNum() + 1);
    }
    
    public int getPageStartIndex() {
        return (this.getPageNum() - this.getFirstPageNum()) * this.getPageSize();
    }
    
    public int getPageEndIndex() {
        return this.getPageStartIndex() + this.getItems().size();
    }
    
    public int getPrevPageEndIndex() {
        return this.isFirstPage() ? 0 : (this.getPageStartIndex() - 1);
    }
    
    public int getNextPageStartIndex() {
        return this.isLastPage() ? this.getPageEndIndex() : (this.getPageEndIndex() + 1);
    }
    
    @Override
    public Iterator<E> iterator() {
        return this.items.iterator();
    }
    
    @Override
    public String toString() {
        return "Page[" + this.getPageNum() + "]:" + this.items.toString();
    }
    
    @Override
    public boolean isEmpty() {
        return this.items.isEmpty();
    }
}
