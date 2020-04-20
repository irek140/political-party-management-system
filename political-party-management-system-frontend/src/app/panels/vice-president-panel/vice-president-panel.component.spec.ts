import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VicePresidentPanelComponent } from './vice-president-panel.component';

describe('VicePresidentPanelComponent', () => {
  let component: VicePresidentPanelComponent;
  let fixture: ComponentFixture<VicePresidentPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VicePresidentPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VicePresidentPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
