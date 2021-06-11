/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} val
 * @return {ListNode}
 */
var removeElements = function (head, val) {
    const preHead = {};
    preHead.next = head;
    let preNode = preHead;
    let node = head;
    while (node !== null)
    {
        if (node.val === val)
        {
            preNode.next = node.next;
        } else
        {
            preNode = node;
        }
        node = node.next;
    }
    return preHead.next;
};
