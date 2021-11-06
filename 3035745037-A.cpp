#include <iostream>
#include <vector>
#include <math.h>
using namespace std;

// Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(NULL), right(NULL)  {}
    TreeNode(int x) : val(x), left(NULL), right(NULL)  {}
};


TreeNode* construct_tree(){
    int n;
    cin >> n;
    int curr_inp;
    vector<TreeNode*> vec;
    for (int i = 0; i < n; i++) {
        cin >> curr_inp;
        if (curr_inp != 0) vec.push_back(new TreeNode(curr_inp));
        else vec.push_back(NULL);
    }

    for(int i = 0; i< floor(n/2);i++ )
    {
        if(vec[i] == NULL) continue;
        vec[i]->left = vec[2*i+1];
        vec[i]->right = vec[2*i+2];
    }
    return vec[0];

}

void inOrderTraverse(TreeNode* root, vector<int>& inOrderList){ //In order traverse to the tree, stores the order in the vector
    if(root == NULL) return;
    inOrderTraverse(root->left, inOrderList);
    inOrderList.push_back(root->val);
    inOrderTraverse(root->right, inOrderList);
}


void find_two_eles(TreeNode* root){
    vector<int> inOrderList;
    inOrderTraverse(root, inOrderList); //As it is a BST, after this line is executed the vector should have elements in ascending order except that two swapped elements

    //There are two cases. 1: the swapped elements are adjacent to each other. 2: the swapped elemenst are not adjacent to each other.
    //For case 1 there is only one decreasing element relative to the next element. For case 2 there are two decreasing elements relative to the next element.
    bool firstNumberFound = false;
    int indexOfFirstNum = -1, indexOfSecondNum = -1;
    for(int i = 0; i < (int) inOrderList.size() - 1; i++){
        if(inOrderList[i] > inOrderList[i+1]){
            if(firstNumberFound == false){ 
                indexOfFirstNum = i;
                firstNumberFound = true;
            }
            else{
                indexOfSecondNum = i + 1;
                break; //All information needed is found
            }

        }
    }

    if(indexOfSecondNum == -1) //Second number not found. Case 1
        cout << inOrderList[indexOfFirstNum+1] << ' ' << inOrderList[indexOfFirstNum];
    else //Second number found. Case 2
        cout << inOrderList[indexOfSecondNum] << ' ' << inOrderList[indexOfFirstNum];

    return;
}




int main() {
    TreeNode* root = construct_tree();
    find_two_eles(root);
    return 0;
}