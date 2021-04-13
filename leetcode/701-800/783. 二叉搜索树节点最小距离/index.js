/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number}
 */
var minDiffInBST = function (root) {
    var vals = [];
    var nodes = [root];
    var nodeCount = 1;
    while (nodeCount !== 0)
    {
        var newNodes = [];
        var newNodeCount = 0;
        for (var node of nodes)
        {
            vals.push(node.val);
            if (node.left !== null)
            {
                newNodes.push(node.left);
                newNodeCount++;
            }
            if (node.right !== null)
            {
                newNodes.push(node.right);
                newNodeCount++;
            }
        }
        nodes = newNodes;
        nodeCount = newNodeCount;
    }
    vals.sort((n1, n2) => n1 - n2);
    var valCount = vals.length;
    var result = vals[1] - vals[0];
    for (var i = 2; i < valCount; i++)
    {
        result = Math.min(result, vals[i] - vals[i - 1]);
    }
    return result;
};
