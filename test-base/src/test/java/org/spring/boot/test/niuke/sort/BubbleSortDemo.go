package main

import "fmt"

func main() {
	arr := []int{9, 20, 3, -10, 0, 15, 7}
	fmt.Println(arr)
	bubbleSort(arr)
	fmt.Println(arr)
}

func bubbleSort(arr []int) {
	length := len(arr)
	for i := 1; i < length; i++ {
		// 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
		flag := true
		for j := 0; j < length-1; j++ {
			if arr[j] > arr[j+1] {
				arr[j], arr[j+1] = arr[j+1], arr[j]
				flag = false
			}
		}
		fmt.Printf("arr = %v \n", arr)
		if flag {
			break
		}
	}
}
