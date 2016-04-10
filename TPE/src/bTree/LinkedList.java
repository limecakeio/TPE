package bTree;

public class LinkedList {

	private ListNode head;

	// LINKED LIST SET UP

	public LinkedList(){
		head = null;
	}

	public LinkedList(Integer o){		
		ListNode element = new ListNode(o,null);
		this.head = element;
	}

	// LINKED LIST METHODS

	public void addFirst(Integer value){

		ListNode element = new ListNode();

		// define node contents
		element.setValue(value);
		element.setNext(head);
		head = element;
	}

	public void addLast(Integer value){

		ListNode element = new ListNode(value, null);
		ListNode pointer = head;

		// list is empty
		if (pointer == null){
			head = element;
		}
		// list contains elements: find the tail
		else {
			while (pointer.getNext() != null){
				pointer = pointer.getNext();
			}
			// element found
			pointer.setNext(element);
		}
	}

	// TODO Remaining Linked List Methods



}
