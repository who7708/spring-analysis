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
	for i := 0; i < length; i++ {
		for j := i; j < length; j++ {
			if arr[i] > arr[j] {
				arr[i], arr[j] = arr[j], arr[i]
			}
		}
	}
}
