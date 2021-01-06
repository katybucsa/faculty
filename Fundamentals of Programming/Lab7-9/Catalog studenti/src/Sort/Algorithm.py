from enum import Enum,unique
from Sort.BubbleSort import BubbleSort
from Sort.ShellSort import ShellSort


@unique 
class Algorithm(Enum):
    BUBBLE_SORT = BubbleSort
    SHELL_SORT = ShellSort