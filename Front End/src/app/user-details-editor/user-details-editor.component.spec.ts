import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDetailsEditorComponent } from './user-details-editor.component';

describe('UserDetailsEditorComponent', () => {
  let component: UserDetailsEditorComponent;
  let fixture: ComponentFixture<UserDetailsEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserDetailsEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDetailsEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
