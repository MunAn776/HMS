package nan.Utils;

public class PageTool {
	private int totalCount;//总数据量
	private int currentPage;//当前页码
	private int pageCount;//总页数
	private int lastPage;//上一页页码
	private int nextPage;//下一页页码
	private int startIndex;//起始下标
	private int pageSize;//每页数据量
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public PageTool() {
		// TODO Auto-generated constructor stub
	}
	//构造方法
	public PageTool(int totalCount, String currentPage) {
		super();
		this.totalCount = totalCount;
		this.currentPage = Integer.valueOf(currentPage);//初始化当前页码
		pageSize = 6;//将每页数据量固定
		initialPageCount();//初始化总页数
		initialLastPage();//初始化上一页页码
		initialNextPage();//初始化下一页页码
		initialStartIndex();//初始化起始下标
	}
	
	private void initialStartIndex() {
		startIndex = (currentPage - 1) * pageSize;
	}
	private void initialNextPage() {
		if (currentPage == pageCount) {
			nextPage = pageCount;
		} else {
			nextPage = currentPage + 1;
		}
	}
	private void initialLastPage() {
		if (currentPage == 1) {
			lastPage = 1;
		} else {
			lastPage = currentPage - 1;
		}
	}
	private void initialPageCount() {
		pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
	}
	
	@Override
	public String toString() {
		return "PageTool [totalCount=" + totalCount + ", currentPage=" + currentPage + ", pageCount=" + pageCount
				+ ", lastPage=" + lastPage + ", nextPage=" + nextPage + ", startIndex=" + startIndex + ", pageSize="
				+ pageSize + "]";
	}
}
