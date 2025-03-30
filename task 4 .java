def is_valid(grid, r, c, k):
    not_in_row = k not in grid[r]
    
    not_in_column = k not in [grid[i][c] for i in range(9)]
    
    start_row = (r // 3) * 3
    start_col = (c // 3) * 3
    not_in_box = k not in [grid[i][j] for i in range(start_row, start_row + 3) for j in range(start_col, start_col + 3)]
    
    return not_in_row and not_in_column and not_in_box

def solve(grid, r=0, c=0):
    if r == 9:
        return True
    elif c == 9:
        return solve(grid, r + 1, 0)
    elif grid[r][c] != 0:
        return solve(grid, r, c + 1)
    else:
        for k in range(1, 10):
            if is_valid(grid, r, c, k):
                grid[r][c] = k
                if solve(grid, r, c + 1):
                    return True
                grid[r][c] = 0
        
        return False
