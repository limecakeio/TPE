package bTree;

public class ListNode {

	private Integer value;
	private ListNode next;

	// SET UP LIST NODE
	public ListNode(Integer value, ListNode next){

		this.value = value;
		this.next = next;
	}

	public ListNode(){

		value = 0;
		next = null;
	} 

	// LIST NODE METHODS
	
	public Integer getValue(){
		return value;
	}

	public void setValue(Integer value){
		this.value = value;
	}

	public ListNode getNext(){
		return next;
	}

	public void setNext(ListNode next){
		this.next = next;
	}	

}
