import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SortableTableComponent } from './sortable-table.component';
import { By } from '@angular/platform-browser';
import { Component } from '@angular/core';

describe('SortableTableComponent', () => {
  let component: SortableTableComponent;
  let fixture: ComponentFixture<SortableTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SortableTableComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SortableTableComponent);
    component = fixture.componentInstance;
    component.title = 'Test Table';
    component.data = [
      { name: 'Alpha', created: '2023-01-01' },
      { name: 'Bravo', created: '2022-01-01' },
    ];
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should render the title', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.textContent).toContain('Test Table');
  });

  it('should toggle sort direction on same field', () => {
    component.sort('name');
    expect(component.currentSortField).toBe('name');
    expect(component.currentDirection).toBe('asc');

    component.sort('name');
    expect(component.currentDirection).toBe('desc');
  });

  it('should change sort field and reset to asc', () => {
    component.sort('created');
    expect(component.currentSortField).toBe('created');
    expect(component.currentDirection).toBe('asc');
  });
});
