package main

import "fmt"

func main() {
	arr := []int{9, 20, 3, -10, 0, 15, 7}
	shellSort(arr)
}

func shellSort(arr []int) []int {
	length := len(arr)
	gap := 1
	for gap < length/3 {
		gap = gap*3 + 1
	}
	for gap > 0 {
		for i := gap; i < length; i++ {
			temp := arr[i]
			j := i - gap
			for j >= 0 && arr[j] > temp {
				arr[j+gap] = arr[j]
				j -= gap
			}
			arr[j+gap] = temp
			fmt.Printf("arr = %v \n", arr)
		}
		gap = gap / 3
	}
	return arr
}
