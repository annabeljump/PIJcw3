/**
 *  A class implementing List interface using pointers.
 *  By Annabel Jump for Programming in Java Coursework 3
 *  @author Annabel Jump
 */

public class LinkedList implements List {
    /**
     * Previous version unnecessarily complicated
     * New class needed = ObjectNode
     * Including getters, setters
     */

    private ObjectNode head;

    public LinkedList() {
        head = null;
    }

    //new class for LinkedList
    public class ObjectNode {
        private Object thing;
        private ObjectNode next;

        public ObjectNode(Object thing) {
            this.thing = thing;
            this.next = null;
        }

        public Object getObject() {
            return thing;
        }

        public ObjectNode getNext() {
            return next;
        }

        public void setNext(ObjectNode newThing) {
            this.next = newThing;
        }

        /**method to determine how many variables in list (for size() below)
         * @return how many variables in list
         */
        public int howMany() {
            if(next == null){
                return 1;
            } else {
                return 1 + next.howMany();
            }
        }

        /**
         * method to retrieve variable at certain point in list
         * @param index (int position)
         * @return the variable at the position
         */
        public ObjectNode getNode(int index){
            if(next == null){
                return null;
            } else if(index == 0) {
                return this;
            } else {
                return next.getNode(index - 1);
            }
        }

        /**
         * Method to update pointers after removal of item in list
         * Invoked by remove() below
         */
        public void ObjectNode bypassNode(int index){

        }
    }

    /**
     * @return true if the list is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        if(head == null){
            return true;
        } else {
            return false;
        }
    }


    /**
     * @return the number of items currently in the list
     */
    @Override
    public int size() {
        if(head == null){
            return 0;
        } else {
            return head.howMany();
        }
    }

    /**
     * Returns the elements at the given position.
     *
     * @param index the position in the list of the item to be retrieved
     * @return the element or an appropriate error message,
     *         encapsulated in a ReturnObject
     */
    @Override
    public ReturnObject get(int index){
        //determines whether requested position exists
        if(index < 0 || index >= size){
            return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
        } else if(isEmpty()) {
            return new ReturnObjectImpl(ErrorMessage.EMPY_STRUCTURE);
        } else {
            // returns object at index position (method in ObjectNode class)
            ObjectNode element = head.getNode(index);
            return new ReturnObjectImpl(element.getObject());
        }
    }
    /**
     * Returns the elements at the given position and removes it
     * from the list. The indeces of elements after the removed
     * element must be updated accordignly.
     *
     * @param index the position in the list of the item to be retrieved
     * @return the element or an appropriate error message,
     *         encapsulated in a ReturnObject
     */
    @Override
    public ReturnObject remove(int index){
        /**
         * As in ArrayList, use get method to supply element to return
         * (or return error for invalid request)
         */
        ReturnObject removedObject = get(index);
        //If no error returned, invoke updating of pointers
        if(!removedObject.hasError()){
            ObjectNode update = head.bypassNode(index);
            return removedObject;
        } else {
            return removedObject;
        }
    }
}