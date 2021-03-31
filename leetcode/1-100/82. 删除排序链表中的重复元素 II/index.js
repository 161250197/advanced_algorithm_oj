/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var deleteDuplicates = function (head) {
    const preHead = Object.create(null);
    preHead.next = head;

    let preNode = preHead;
    let node = head;
    while (node !== null && node.next)
    {
        let repeated = false;
        if (node.val === node.next.val)
        {
            repeated = true;
            while (node.next && node.val === node.next.val)
            {
                node.next = node.next.next;
            }
        }
        if (repeated)
        {
            preNode.next = node.next;
        } else
        {
            preNode = node;
        }
        node = preNode.next;
    }

    return preHead.next;
};
