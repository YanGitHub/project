package kj.pos.entity;

/**
 * Created by Yan on 15-12-3.
 */
public class PageUtil {
    //当前页数默认为第一页
    private int page = 1;

    //当前页数数据,默认为10
    private int rows = 10;

    //数据汇总
    private int total;

    //开始条数
    private int startRow;

    //结束条数
    private int endRow;

    public PageUtil(int page, int rows, int total) {
        if(page < 1){
            page = 1;
        }
        this.page = page;
        this.rows = rows;
        this.total = total;
        this.startRow = (page - 1) * rows;
        this.endRow = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }
}
