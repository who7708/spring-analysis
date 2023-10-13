package main

import "fmt"

func main() {
	arr := []int{-10, 9, 20, 3, 0, 15, 7}
	fmt.Println(arr)
	selectSortDemo(arr)
	fmt.Println(arr)
}

func selectSortDemo(arr []int) {
	length := len(arr)
	for i := 0; i < length; i++ {
		minIdx := i
		for j := i; j < length; j++ {
			if arr[minIdx] > arr[j] {
				minIdx = j
			}
		}

		if minIdx != i {
			arr[i], arr[minIdx] = arr[minIdx], arr[i]
		}
	}
}
