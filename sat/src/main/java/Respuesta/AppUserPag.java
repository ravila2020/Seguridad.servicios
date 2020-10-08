package Respuesta;

import java.util.List;

import com.mtto.sat.modelo.AppUser;

public class AppUserPag {

	int page;
	int perPage;
	int total;
	int totalPages;
	private List<AppUser> users;
	
	
	
	public AppUserPag() {
		this.page = page;
		this.perPage = perPage;
		this.total = total;
		this.totalPages = totalPages;
		this.users = users;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<AppUser> getUsers() {
		return users;
	}
	public void setUsers(List<AppUser> users) {
		this.users = users;
	}
	
	
}
