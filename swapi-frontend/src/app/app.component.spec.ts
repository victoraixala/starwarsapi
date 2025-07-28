import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AppComponent],
      imports: [FormsModule, HttpClientTestingModule],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the app', () => {
    expect(component).toBeTruthy();
  });

  it('should render header text', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('SWAPI Explorer');
  });

  it('should update peopleSearch on input', () => {
    component.peopleSearch = 'Luke';
    fixture.detectChanges();
    expect(component.peopleSearch).toBe('Luke');
  });

  it('should update planetsSearch on input', () => {
    component.planetsSearch = 'Tatooine';
    fixture.detectChanges();
    expect(component.planetsSearch).toBe('Tatooine');
  });
});
