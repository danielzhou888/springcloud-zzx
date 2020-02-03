package com.zzx.tools.page;

import java.io.Serializable;
import java.util.List;

/**
 * <p>请求分页VO基类</p>
 * @author: zhouzhixiang
 * @date: 2019-12-10
 * @company: 叮当快药科技集团有限公司
 **/
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -5533189362034206526L;
    private List<T> list;
    private int pageNumber = 1;
    private int pageSize = 20;
    private int totalPage;
    private int totalRow;
    private int start;
    private int end;

    public Page(List<T> list, int pageNumber, int pageSize, int totalPage, int totalRow) {
        this.list = list;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalRow = totalRow;
        this.start = (pageNumber - 1) * pageSize;
        this.end = pageSize;
    }

    public Page() {
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.totalPage = (this.totalRow + pageSize - 1) / this.pageSize;
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        this.totalPage = (this.totalRow + this.pageSize - 1) / this.pageSize;
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalPage = (totalRow + this.pageSize - 1) / this.pageSize;
        this.totalRow = totalRow;
    }

    public boolean isFirstPage() {
        return this.pageNumber == 1;
    }

    public boolean isLastPage() {
        return this.pageNumber >= this.totalPage;
    }

    public int getStart() {
        this.start = (this.pageNumber - 1) * this.pageSize;
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        this.end = this.pageSize;
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("pageNumber : ").append(this.pageNumber);
        msg.append("\npageSize : ").append(this.pageSize);
        msg.append("\ntotalPage : ").append(this.totalPage);
        msg.append("\ntotalRow : ").append(this.totalRow);
        return msg.toString();
    }
}
