import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-sortable-table',
  templateUrl: './sortable-table.component.html',
  styleUrls: ['./sortable-table.component.scss']
})
export class SortableTableComponent {
  @Input() data: any[] = [];
  @Input() title: string = '';

  currentSortField: string = '';
  currentDirection: 'asc' | 'desc' = 'asc';

  sort(field: string): void {
    if (this.currentSortField === field) {
      this.currentDirection = this.currentDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.currentSortField = field;
      this.currentDirection = 'asc';
    }
  }
}
