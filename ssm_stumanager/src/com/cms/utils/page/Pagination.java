// 
// 
// 

package com.cms.utils.page;

public class Pagination<E> extends AbstractPage<E>
{
    protected int start;
    protected int totalItemsCount;
    protected int totalPageCount;
    
    public int getTotalItemsCount() {
        return this.totalItemsCount;
    }
    
    public void setTotalItemsCount(final int totalItemsCount) {
        this.totalItemsCount = totalItemsCount;
        this.totalPageCount = (this.getTotalItemsCount() - 1) / this.getPageSize() + this.getFirstPageNum();
    }
    
    @Override
    public boolean isLastPage() {
        return this.getLastPageNum() <= this.getPageNum();
    }
    
    @Override
    public int getLastPageNum() {
        return this.totalPageCount;
    }
    
    @Override
    public String toString() {
        return String.format("Page[%d] of [%d] in total [%d] :%s", this.getPageNum(), this.getLastPageNum(), this.getTotalItemsCount(), this.items.toString());
    }
    
    public int getTotalPageCount() {
        return this.totalPageCount;
    }
    
    public int getStart() {
        return this.start = (this.pageNum - 1) * this.pageSize;
    }
}
