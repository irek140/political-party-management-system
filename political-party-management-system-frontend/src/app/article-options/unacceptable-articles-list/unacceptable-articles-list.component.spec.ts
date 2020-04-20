import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnacceptableArticlesListComponent } from './unacceptable-articles-list.component';

describe('UnacceptableArticlesListComponent', () => {
  let component: UnacceptableArticlesListComponent;
  let fixture: ComponentFixture<UnacceptableArticlesListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnacceptableArticlesListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnacceptableArticlesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
