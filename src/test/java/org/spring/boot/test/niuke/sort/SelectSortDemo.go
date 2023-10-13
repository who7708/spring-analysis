package main

import "fmt"

func main() {
	arr := []int{9, 20, 3, -10, 0, 15, 7}
	selectSortDemo(arr)
}

func selectSortDemo(arr []int) {
	fmt.Printf("arr = %v \n", arr)
	length := len(arr)
	for i := 0; i < length-1; i++ {
		minIdx := i
		for j := i + 1; j < length; j++ {
			if arr[minIdx] > arr[j] {
				minIdx = j
			}
		}

		if minIdx != i {
			arr[i], arr[minIdx] = arr[minIdx], arr[i]
		}
		fmt.Printf("arr = %v \n", arr)
	}
}
