package othertest.arithmetic.search;

/**
 * Sunday算法是从前往后匹配，在匹配失败时关注的是主串中参加匹配的最末位字符的下一位字符。
 *
 * 如果该字符没有在模式串中出现则直接跳过，即移动位数 = 模式串长度 + 1；
 * 否则，其移动位数 = 模式串长度 - 该字符最右出现的位置(以0开始) = 模式串中该字符最右出现的位置到尾部的距离 + 1。
 *
 *
 *
 */
public class Sunday {


    static final int ASCII_SIZE = 126;

    int sunday(char[] total,char[] part){
        int tSize = total.length;
        int pSize = part.length;
        int[] move = new int[ASCII_SIZE];
        //主串参与匹配最末位字符移动到该位需要移动的位数
        for (int i= 0;i<ASCII_SIZE;i++){
            move[i] = pSize+1;
        }

        for (int i = 0;i<pSize;i++){
            move[part[i]] = pSize-i;
        }

        int s = 0;//模式串头部在字符串位置

        int j;//模式串已经匹配了的长度

        while(s<=tSize-pSize){//到达末尾之前
            j = 0;
            while(total[s+j]==part[j]){
                j++;
                if (j>=pSize){
                    return s;
                }
            }
            s+=move[total[s+pSize]];
        }
        return -1;
    }
}
