#include <stdio.h>
#include <assert.h>
#include <algorithm>

struct node
{
    int key;
    int height;
    int size;
    node *left, *right;
    node(int k)
    {
        key = k;
        height = 1;
        size = 1; //Does this even matter?? wtf is this for???
        left = right = 0;
    }
};

int height(node* r)
{
    return r ? r->height : 0;
}

void update_height(node* root)
{
    if(height(root->left) > height(root->right))
        root->height = height(root->left) + 1;
    else
        root->height = height(root->right) + 1;
}

int sizeOfTree(node* root){
    return root == NULL? 0 : root->size;
}

void right_rotate(node*& ref_root) //parent NOT YET UPDATED
{
    node* upperNode = ref_root;
    node* lowerNode = ref_root->left;
    upperNode->left = lowerNode->right;
    lowerNode->right = upperNode;
    update_height(upperNode);
    update_height(lowerNode);
    ref_root = lowerNode;
}

void left_rotate(node*& ref_root)
{
    node* upperNode = ref_root;
    node* lowerNode = ref_root->right;
    upperNode->right = lowerNode->left;
    lowerNode->left = upperNode;
    update_height(upperNode);
    update_height(lowerNode);
    ref_root = lowerNode;
}

//after deletion and insertion, maintain a balanced tree.
void maintain(node*& ref_root)
{
    if(height(ref_root->left) == height(ref_root->right) + 2){
        if(height(ref_root->left->left) >= height(ref_root->left->right))
            right_rotate(ref_root);
        else{
            left_rotate(ref_root->left);
            right_rotate(ref_root);
        }
    }
    else if(height(ref_root->left) == height(ref_root->right) - 2){
        if(height(ref_root->right->right) >= height(ref_root->right->left))
            left_rotate(ref_root);
        else{
            right_rotate(ref_root->right);
            left_rotate(ref_root);
        }
    }
}

void insert_key(int key, node*& ref_root)
{
    if(ref_root == NULL){ //Null tree
        node* newNode = new node(key);
        ref_root = newNode;
        return;
    }

    if(ref_root->key > key){
        if(ref_root->left != NULL){
            insert_key(key, ref_root->left);
        }
        else{
            node* newNode = new node(key);
            ref_root->left = newNode;
        }
    }
    else{
        if(ref_root->right != NULL){
            insert_key(key, ref_root->right);
        }
        else{
            node* newNode = new node(key);
            ref_root->right = newNode;
        }
    }

    update_height(ref_root);
    maintain(ref_root);
    return;
}

void deleteKeyHelper(node*& ref_root);//Prototype

int twoChildrenDeleteKeyHelper(node*& ref_root){ //Find Successor, update height and maintain balance if necessary
    int returnValue;
    if(ref_root->left != NULL){
        returnValue = twoChildrenDeleteKeyHelper(ref_root->left);
        update_height(ref_root);
        maintain(ref_root);
    }
    else{
        returnValue = ref_root->key;
        deleteKeyHelper(ref_root);
    }
    return returnValue;
}

void deleteKeyHelper(node*& ref_root){
    if(ref_root->left != NULL && ref_root->right == NULL){ //1 child
        node* nodeToBeRemoved = ref_root;
        ref_root = ref_root->left;
        delete nodeToBeRemoved;
    }
    else if(ref_root->left == NULL && ref_root->right != NULL){ //1 child
        node* nodeToBeRemoved = ref_root;
        ref_root = ref_root->right;
        delete nodeToBeRemoved;
    }
    else if(ref_root->left == NULL && ref_root->right == NULL){ //0 child
        delete ref_root;
        ref_root = NULL;
    }
    else{ //2 children
        ref_root->key = twoChildrenDeleteKeyHelper(ref_root->right);
    }
}

void delete_key(int key, node*& ref_root)
{
    if(ref_root->key == key){ //root is the key
        deleteKeyHelper(ref_root);
    }
    else if(ref_root->key > key){
        if(ref_root->left->key == key)
            deleteKeyHelper(ref_root->left);
        else
            delete_key(key, ref_root->left);
    }
    else{
        if(ref_root->right->key == key)
            deleteKeyHelper(ref_root->right);
        else
            delete_key(key, ref_root->right);
    }

    update_height(ref_root);
    maintain(ref_root);
    return;
}

int KthMin(node * root, int& k){
    if(root == NULL) return -1;

    int leftSubTree = KthMin(root->left, k);
    if(leftSubTree != -1) return leftSubTree;

    if(k == 1) return root->key;
    k = k - 1;

    int rightSubTree = KthMin(root->right, k);
    if(rightSubTree != -1) return rightSubTree;
    return -1;
}

int main()
{
    node *root = 0;
    char op[10] = "";
    int k;
    while(true)
    {
        scanf("%s", op);
        if(op[0] == 'E') break;
        switch(op[0])
        {
            case 'A': scanf("%d", &k); insert_key(k, root); break;
            case 'D': scanf("%d", &k); delete_key(k, root); break;
            case 'M': scanf("%d", &k); printf("%d\n", KthMin(root, k));break;
            default: assert(0);
        }
    }
    return 0;
}
