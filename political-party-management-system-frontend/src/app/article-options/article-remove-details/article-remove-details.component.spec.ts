import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleRemoveDetailsComponent } from './article-remove-details.component';

describe('ArticleRemoveDetailsComponent', () => {
  let component: ArticleRemoveDetailsComponent;
  let fixture: ComponentFixture<ArticleRemoveDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArticleRemoveDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticleRemoveDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
