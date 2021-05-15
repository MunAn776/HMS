package nan.Utils;

public class PageTool {
	private int totalCount;//��������
	private int currentPage;//��ǰҳ��
	private int pageCount;//��ҳ��
	private int lastPage;//��һҳҳ��
	private int nextPage;//��һҳҳ��
	private int startIndex;//��ʼ�±�
	private int pageSize;//ÿҳ������
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
	//���췽��
	public PageTool(int totalCount, String currentPage) {
		super();
		this.totalCount = totalCount;
		this.currentPage = Integer.valueOf(currentPage);//��ʼ����ǰҳ��
		pageSize = 6;//��ÿҳ�������̶�
		initialPageCount();//��ʼ����ҳ��
		initialLastPage();//��ʼ����һҳҳ��
		initialNextPage();//��ʼ����һҳҳ��
		initialStartIndex();//��ʼ����ʼ�±�
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
