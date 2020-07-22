package uestc.learning.web;

import java.util.List;

public class Page<T> {
	private int curPage;//��ǰҳ��
	private int pageSize;//ÿҳ����������
	private int totalSize;//һ������������
	private int totalPage;//һ������ҳ
	private List<T> data;//��ǰ������
	
	public Page(List<T> data,int totalSize,int pageSize,int curPage) {
		this.data = data;
		this.pageSize = pageSize;
		this.totalSize = totalSize;
		this.curPage = curPage;
		this.totalPage = (totalSize % pageSize == 0) ? (totalSize / pageSize):(totalSize / pageSize + 1);
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
