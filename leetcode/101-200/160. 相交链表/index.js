/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} headA
 * @param {ListNode} headB
 * @return {ListNode}
 */
var getIntersectionNode = function (headA, headB) {
    if (!(headA && headB))
    {
        return null;
    }
    if (headA === headB)
    {
        return headA;
    }
    let nodeA = headA;
    let lenA = 1;
    while (nodeA.next)
    {
        nodeA = nodeA.next;
        lenA++;
    }
    let nodeB = headB;
    let lenB = 1;
    while (nodeB.next)
    {
        nodeB = nodeB.next;
        lenB++;
    }
    if (nodeA !== nodeB)
    {
        return null;
    }
    nodeA = headA;
    nodeB = headB;
    if (lenA > lenB)
    {
        while (lenA !== lenB)
        {
            nodeA = nodeA.next;
            lenA--;
        }
    }
    if (lenA < lenB)
    {
        while (lenA !== lenB)
        {
            nodeB = nodeB.next;
            lenB--;
        }
    }
    while (nodeA !== nodeB)
    {
        nodeA = nodeA.next;
        nodeB = nodeB.next;
    }
    return nodeA;
};
