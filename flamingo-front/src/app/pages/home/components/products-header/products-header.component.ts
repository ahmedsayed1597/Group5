import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-products-header',
  templateUrl: './products-header.component.html',
})
export class ProductsHeaderComponent {
  @Output() columnsCountChange = new EventEmitter<number>();
  @Output() itemsCountChange = new EventEmitter<number>();
  @Output() previousPage = new EventEmitter<number>();
  @Output() sortChange = new EventEmitter<string>();
  @Input() pageNumber:number;
  itemsShowCount = 0;
  sort = 'desc';

  constructor() {}

  onColumnsUpdated(colsNum: number): void {
    this.columnsCountChange.emit(colsNum);
  }

  onItemsNext(): void {
    this.itemsShowCount++;
    this.itemsCountChange.emit(this.itemsShowCount);
  }

  onItemsPrevious(): void {
    this.itemsShowCount--;
    this.previousPage.emit(this.itemsShowCount);
  }

  onSortUpdated(newSort: string): void {
    this.sortChange.emit(newSort);
    this.sort = newSort;
  }
}
